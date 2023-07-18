import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBatches } from '../batches.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../batches.test-samples';

import { BatchesService, RestBatches } from './batches.service';

const requireRestSample: RestBatches = {
  ...sampleWithRequiredData,
  catalogCreatedtime: sampleWithRequiredData.catalogCreatedtime?.toJSON(),
  orderedTime: sampleWithRequiredData.orderedTime?.toJSON(),
  startedTime: sampleWithRequiredData.startedTime?.toJSON(),
  modifiedTime: sampleWithRequiredData.modifiedTime?.toJSON(),
  suspendedTime: sampleWithRequiredData.suspendedTime?.toJSON(),
  finishedTime: sampleWithRequiredData.finishedTime?.toJSON(),
};

describe('Batches Service', () => {
  let service: BatchesService;
  let httpMock: HttpTestingController;
  let expectedResult: IBatches | IBatches[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BatchesService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Batches', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const batches = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(batches).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Batches', () => {
      const batches = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(batches).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Batches', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Batches', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Batches', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBatchesToCollectionIfMissing', () => {
      it('should add a Batches to an empty array', () => {
        const batches: IBatches = sampleWithRequiredData;
        expectedResult = service.addBatchesToCollectionIfMissing([], batches);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(batches);
      });

      it('should not add a Batches to an array that contains it', () => {
        const batches: IBatches = sampleWithRequiredData;
        const batchesCollection: IBatches[] = [
          {
            ...batches,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBatchesToCollectionIfMissing(batchesCollection, batches);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Batches to an array that doesn't contain it", () => {
        const batches: IBatches = sampleWithRequiredData;
        const batchesCollection: IBatches[] = [sampleWithPartialData];
        expectedResult = service.addBatchesToCollectionIfMissing(batchesCollection, batches);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(batches);
      });

      it('should add only unique Batches to an array', () => {
        const batchesArray: IBatches[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const batchesCollection: IBatches[] = [sampleWithRequiredData];
        expectedResult = service.addBatchesToCollectionIfMissing(batchesCollection, ...batchesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const batches: IBatches = sampleWithRequiredData;
        const batches2: IBatches = sampleWithPartialData;
        expectedResult = service.addBatchesToCollectionIfMissing([], batches, batches2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(batches);
        expect(expectedResult).toContain(batches2);
      });

      it('should accept null and undefined values', () => {
        const batches: IBatches = sampleWithRequiredData;
        expectedResult = service.addBatchesToCollectionIfMissing([], null, batches, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(batches);
      });

      it('should return initial array if no Batches is added', () => {
        const batchesCollection: IBatches[] = [sampleWithRequiredData];
        expectedResult = service.addBatchesToCollectionIfMissing(batchesCollection, undefined, null);
        expect(expectedResult).toEqual(batchesCollection);
      });
    });

    describe('compareBatches', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBatches(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareBatches(entity1, entity2);
        const compareResult2 = service.compareBatches(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareBatches(entity1, entity2);
        const compareResult2 = service.compareBatches(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareBatches(entity1, entity2);
        const compareResult2 = service.compareBatches(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
